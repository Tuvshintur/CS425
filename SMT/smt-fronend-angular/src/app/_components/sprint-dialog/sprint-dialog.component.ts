import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Sprint} from '../../_models';
import {AuthenticationService, ProjectService} from '../../_services';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {DatePipe} from '@angular/common';
import {SprintService} from '../../_services/sprint.service';

@Component({
    selector: 'app-sprint-dialog',
    templateUrl: './sprint-dialog.component.html',
    styleUrls: ['./sprint-dialog.component.css']
})
export class SprintDialogComponent implements OnInit {

    form: FormGroup;
    sprint: Sprint;
    projectId: string;

    constructor(
        private sprintService: SprintService,
        private fb: FormBuilder,
        private dialogRef: MatDialogRef<SprintDialogComponent>,
        private authenticationService: AuthenticationService,
        @Inject(MAT_DIALOG_DATA) data) {
        this.sprint = data;
    }


    ngOnInit() {
        this.form = this.fb.group(this.sprint);
    }

    close() {
        this.dialogRef.close();
    }

    onSubmit() {
        if (this.form.invalid) {
            console.log('INVALID');
            return;
        }
        console.log(this.form.value);

        this.form.value.startDate = new Date();
        this.form.value.endDate = new Date();

        this.sprintService.addSprint(this.form.value, this.projectId)
            .subscribe(data => {
                console.log(data);
                this.dialogRef.close();
                location.reload();
            });
    }
}
