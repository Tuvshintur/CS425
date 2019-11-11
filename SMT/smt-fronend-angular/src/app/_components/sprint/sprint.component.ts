import {Component, OnInit} from '@angular/core';
import {SprintService} from '../../_services/sprint.service';
import {TaskService} from '../../_services/task.service';
import {Sprint, Task} from '../../_models';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material';
import {Router, Routes} from '@angular/router';
import {SprintDialogComponent} from '../sprint-dialog/sprint-dialog.component';
import {ActivatedRoute} from '@angular/router';
import {AddTaskDialogComponent} from '../add-task-dialog';
import {TasktosprintdialogComponent} from '../tasktosprintdialog';
import {TaskDetailDialogComponent} from '../task-detail-dialog';

@Component({
    selector: 'app-sprint',
    templateUrl: './sprint.component.html',
    styleUrls: ['./sprint.component.css']
})
export class SprintComponent implements OnInit {
    tasks: Task[];
    sprints: Sprint[];

    constructor(
        private sprintService: SprintService,
        private taskService: TaskService,
        private route: ActivatedRoute,
        private dialog: MatDialog
    ) {
    }

    ngOnInit() {
        this.taskService.getTaskProject(this.route.snapshot.params.projectId)
            .subscribe(data => {
                this.tasks = data;
            });

        this.sprintService.getAll(this.route.snapshot.params.projectId)
            .subscribe(data => {
                this.sprints = data;
            });
    }

    addSprint(sprint: Sprint): void {
        this.sprintService.addSprint(sprint, this.route.snapshot.params.projectId)
            .subscribe(data => {
            });
    }

    addSprintDialog(): void {
        const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;
        dialogConfig.data = new Sprint();

        const dialogRef: MatDialogRef<SprintDialogComponent> = this.dialog.open(SprintDialogComponent, dialogConfig);
        dialogRef.componentInstance.projectId = this.route.snapshot.params.projectId;

        dialogRef.afterClosed().subscribe(
            data => console.log('Dialog output:', data)
        );
    }

    addTaskDialog(): void {
        const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;
        dialogConfig.data = new Task();

        const dialogRef: MatDialogRef<AddTaskDialogComponent> = this.dialog.open(AddTaskDialogComponent, dialogConfig);
        dialogRef.componentInstance.projectId = this.route.snapshot.params.projectId;

        dialogRef.afterClosed().subscribe(
            data => console.log('Dialog output:', data)
        );
    }

    showTaskDetailDialog(task: Task): void {
        const dialogConfig = new MatDialogConfig();

        dialogConfig.data = task;
        dialogConfig.width = '500px';
        dialogConfig.panelClass = 'my-modal';
        const dialogRef = this.dialog.open(TaskDetailDialogComponent, dialogConfig);

        dialogRef.afterClosed().subscribe(
            data => console.log('Closed', data)
        );
    }
}
