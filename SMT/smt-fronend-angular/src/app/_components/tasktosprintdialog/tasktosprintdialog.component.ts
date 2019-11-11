import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Sprint, Task} from '../../_models';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {SelectionModel} from '@angular/cdk/collections';
import {TaskService} from '../../_services/task.service';
import {ActivatedRoute} from '@angular/router';

@Component({
    selector: 'app-tasktosprintdialog',
    templateUrl: './tasktosprintdialog.component.html',
    styleUrls: ['./tasktosprintdialog.component.css']
})
export class TasktosprintdialogComponent implements OnInit {
    displayedColumns: string[] = ['select', 'taskId', 'name', 'startDate', 'dueDate', 'createdUser', 'createdDate'];
    selection = new SelectionModel<Task>(true, []);
    dataSource: MatTableDataSource<Task>;
    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
    @ViewChild(MatSort, {static: true}) sort: MatSort;

    sprintId: string;

    constructor(public dialogRef: MatDialogRef<TasktosprintdialogComponent>,
                @Inject(MAT_DIALOG_DATA) public tasks: Task[],
                private taskService: TaskService,
                private route: ActivatedRoute) {
        this.dataSource = new MatTableDataSource<Task>(tasks);
    }

    ngOnInit() {
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();

        if (this.dataSource.paginator) {
            this.dataSource.paginator.firstPage();
        }
    }

    isAllSelected() {
        const numSelected = this.selection.selected.length;
        const numRows = this.dataSource.data.length;
        return numSelected === numRows;
    }

    masterToggle() {
        this.isAllSelected() ?
            this.selection.clear() :
            this.dataSource.data.forEach(row => this.selection.select(row));
    }

    checkboxLabel(row?: Task): string {
        if (!row) {
            return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
        }
        return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.taskId + 1}`;
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    addToSprint() {
        this.selection.selected.forEach((task: Task) => {
            task.sprint = new Sprint();
            task.sprint.sprintId = +this.sprintId;
            console.log(this.route.snapshot.params);
        });

        this.taskService.addTaskToSprint(this.selection.selected, this.sprintId)
            .subscribe(data => {
                console.log(data);
                this.dialogRef.close();
                location.reload();
            });

    }
}
