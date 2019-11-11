import {Component, Inject, OnInit} from '@angular/core';
import {AuthenticationService, ProjectService} from '../../_services';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Task} from '../../_models';
import {TaskService} from '../../_services/task.service';

@Component({
    selector: 'app-add-task-dialog',
    templateUrl: './add-task-dialog.component.html',
    styleUrls: ['./add-task-dialog.component.css']
})
export class AddTaskDialogComponent implements OnInit {
    form: FormGroup;
    task: Task;
    projectId: string;

    constructor(
        private taskService: TaskService,
        private fb: FormBuilder,
        private dialogRef: MatDialogRef<AddTaskDialogComponent>,
        private datePipe: DatePipe,
        private authenticationService: AuthenticationService,
        private router: Router,
        @Inject(MAT_DIALOG_DATA) data) {
        this.task = data;
    }

    ngOnInit() {
        this.form = this.fb.group(this.task);
    }

    close() {
        this.dialogRef.close();
    }

    onSubmit() {
        // stop here if form is invalid
        if (this.form.invalid) {
            return;
        }
        this.form.value.createdDate = new Date().toISOString().slice(0, 10);
        this.form.value.dueDate = this.datePipe.transform(this.form.value.dueDate, 'yyyy-MM-dd');
        this.form.value.startDate = this.datePipe.transform(this.form.value.startDate, 'yyyy-MM-dd');
        this.form.value.status = 1;
        const currentUser = localStorage.getItem('currentUser') !== undefined ? JSON.parse(localStorage.getItem('currentUser')) : {};
        if (currentUser) {
            this.form.value.createdUser = currentUser;
            this.form.value.developer = currentUser;
            this.form.value.tester = currentUser;
        }
        console.log(this.form.value);

        this.taskService.addTask(this.form.value, this.projectId).subscribe(data => {
            this.task = data;
            this.dialogRef.close();
            location.reload();
        });
    }
}
