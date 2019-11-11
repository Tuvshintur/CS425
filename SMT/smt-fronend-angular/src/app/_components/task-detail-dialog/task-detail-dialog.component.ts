import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogConfig} from '@angular/material';
import {Task} from '../../_models';
import {ProjectDialogComponent} from '../project-dialog';

@Component({
  selector: 'app-task-detail-dialog',
  templateUrl: './task-detail-dialog.component.html',
  styleUrls: ['./task-detail-dialog.component.css']
})
export class TaskDetailDialogComponent implements OnInit {
  task: Task;

  constructor(
      private dialog: MatDialog,
      @Inject(MAT_DIALOG_DATA) data) {
      this.task = data;
  }
  ngOnInit() {
  }
}
