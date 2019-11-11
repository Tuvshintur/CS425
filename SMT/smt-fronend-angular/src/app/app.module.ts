import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {appRoutingModule} from './app.routing';
import {HomeComponent} from './_components/home';
import {LoginComponent} from './_components/login';
import {RegisterComponent} from './_components/register';
import {ProjectComponent} from './_components/project';
import {ErrorInterceptor, JwtInterceptor} from './_helpers';
import { AlertComponent } from './_components/alert';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { TaskComponent } from './_components/task';
import { ProjectService } from './_services';
import { SprintComponent } from './_components/sprint/sprint.component';
import {SprintService} from './_services/sprint.service';
import {TaskService} from './_services/task.service';
import { TasktosprintdialogComponent } from './_components/tasktosprintdialog';
import { ProjectDialogComponent } from './_components/project-dialog';
import {DatePipe} from '@angular/common';
import {SprintDialogComponent} from './_components/sprint-dialog/sprint-dialog.component';
import { AddTaskDialogComponent } from './_components/add-task-dialog/add-task-dialog.component';
import { TaskDetailDialogComponent } from './_components/task-detail-dialog/task-detail-dialog.component';

@NgModule({
   declarations: [
      AppComponent,
      HomeComponent,
      LoginComponent,
      RegisterComponent,
      ProjectComponent,
      AlertComponent,
      TaskComponent,
      SprintComponent,
      ProjectDialogComponent,
      SprintDialogComponent,
      TasktosprintdialogComponent,
      AddTaskDialogComponent,
      TaskDetailDialogComponent
   ],
   entryComponents: [
      ProjectDialogComponent,
      SprintDialogComponent,
      TasktosprintdialogComponent,
      AddTaskDialogComponent,
      TaskDetailDialogComponent
   ],
   imports: [
      BrowserModule,
      ReactiveFormsModule,
      HttpClientModule,
      appRoutingModule,
      BrowserAnimationsModule,
      MaterialModule
   ],
   providers: [
      {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
      {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
      ProjectService,
      SprintService,
      TaskService,
      DatePipe
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule {
}
