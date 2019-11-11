import {Component, OnInit} from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {Router} from '@angular/router';
import {Sprint, Task} from '../../_models';
import {TaskService} from '../../_services/task.service';
import {TaskStatus} from '../../_models/taskStatus';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material';
import {TasktosprintdialogComponent} from '../tasktosprintdialog';
import {ActivatedRoute} from '@angular/router';
import {SprintService} from '../../_services/sprint.service';
import {TaskDetailDialogComponent} from '../task-detail-dialog';

@Component({
    selector: 'app-task',
    templateUrl: './task.component.html',
    styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

    tasks: Task[] = [];

    new: Task[] = [];

    developing: Task[] = [];

    tobeTested: Task[] = [];

    testing: Task[] = [];

    approved: Task[] = [];

    deployed: Task[] = [];

    tempTask: Task;

    currentSprint: Sprint = new Sprint();

    drop(event: CdkDragDrop<Task[]>, status: string) {
        if (event.previousContainer === event.container) {
            moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
        } else {
            transferArrayItem(event.previousContainer.data,
                event.container.data,
                event.previousIndex,
                event.currentIndex);

            this.tempTask = event.item.data;
            this.tempTask.status = TaskStatus[status];
            this.taskService.changeStatus(this.tempTask)
                .subscribe(data => {
                    console.log(data);
                });
            this.tempTask = null;
        }
    }

    addTaskDialog() {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;

        this.taskService.getTaskProject(String(this.currentSprint.project.projectId))
            .subscribe(data => {
                dialogConfig.data = data;
                let dialogRef: MatDialogRef<TasktosprintdialogComponent> = this.dialog.open(TasktosprintdialogComponent, dialogConfig);
                dialogRef.componentInstance.sprintId = String(this.currentSprint.sprintId);
            });

    }

    constructor(private router: Router,
                private taskService: TaskService,
                private dialog: MatDialog,
                private route: ActivatedRoute,
                private sprintService: SprintService) {

    }

    ngOnInit() {

        this.sprintService.getSprintDetail(this.route.snapshot.params.sprintId)
            .subscribe(data => {
                this.currentSprint = data;
                this.taskService.getTaskSprint(String(this.currentSprint.sprintId))
                    .subscribe(tasks => {
                        this.tasks = tasks;
                        this.tasks.forEach((task) => {
                            if (task.status === TaskStatus.NEW) {
                                this.new.push(task);
                            } else if (task.status === TaskStatus.DEVELOPING) {
                                this.developing.push(task);
                            } else if (task.status === TaskStatus.TOBETESTED) {
                                this.tobeTested.push(task);
                            } else if (task.status === TaskStatus.TESTING) {
                                this.testing.push(task);
                            } else if (task.status === TaskStatus.APPROVED) {
                                this.approved.push(task);
                            } else if (task.status === TaskStatus.DEPLOYED) {
                                this.deployed.push(task);
                            }
                        });
                    });
            });
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
