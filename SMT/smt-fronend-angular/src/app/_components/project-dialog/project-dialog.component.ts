import {Component, Inject, OnInit, ViewEncapsulation} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Project} from '../../_models/project';
import {ProjectStatus} from '../../_models/projectStatus';
import {ProjectService} from '../../_services/project.service';
import {FormBuilder, Validators, FormGroup} from '@angular/forms';
import {DatePipe} from '@angular/common';
import {AuthenticationService} from '../../_services';
import {User} from '../../_models';
import {Router} from '@angular/router';

@Component({
    selector: 'app-project-dialog',
    templateUrl: './project-dialog.component.html',
    styleUrls: ['./project-dialog.component.css']
})
export class ProjectDialogComponent implements OnInit {

    form: FormGroup;
    project: Project;

    constructor(
        private projectService: ProjectService,
        private fb: FormBuilder,
        private dialogRef: MatDialogRef<ProjectDialogComponent>,
        private datePipe: DatePipe,
        private authenticationService: AuthenticationService,
        private router: Router,
        @Inject(MAT_DIALOG_DATA) data) {
        this.project = data;
    }


    ngOnInit() {
        this.form = this.fb.group(this.project);
    }


    save() {
        this.dialogRef.close(this.form.value);
    }

    close() {
        this.dialogRef.close();
    }

    onSubmit() {
        // stop here if form is invalid
        if (this.form.invalid) {
            console.log('INVALID');
            return;
        }
        this.form.value.createdDate = new Date().toISOString().slice(0, 10);
        this.form.value.dueDate = this.datePipe.transform(this.form.value.dueDate, 'yyyy-MM-dd');
        this.form.value.startDate = this.datePipe.transform(this.form.value.startDate, 'yyyy-MM-dd');
        this.form.value.status = 1;
        const currentUser = localStorage.getItem('currentUser') !== undefined ? JSON.parse(localStorage.getItem('currentUser')) : {};
        if (currentUser) {
            this.form.value.createdUser = currentUser;
            this.form.value.scrumMaster = currentUser;
        }
        console.log(this.form.value);

        this.projectService.addProject(this.form.value).subscribe(data => {
            this.project = data;
            this.dialogRef.close();
            location.reload();
        });
    }

}
