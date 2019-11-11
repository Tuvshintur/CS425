import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Project} from '../../_models';
import {ProjectService} from '../../_services';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {ProjectDialogComponent} from '../project-dialog';

@Component({
    selector: 'app-project',
    templateUrl: './project.component.html',
    styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
    projects: Project[];

    constructor(private router: Router,
                private projectService: ProjectService,
                private dialog: MatDialog) {
    }


    ngOnInit() {
        this.projectService.getAllProjects().subscribe(data => {
            this.projects = data;
        });
    }

    addProject(project: Project): void {
        this.projectService.addProject(project)
            .subscribe(data => {
            });
    }

    openProjectDialog(): void {
        const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;

        dialogConfig.data = new Project();

        const dialogRef = this.dialog.open(ProjectDialogComponent, dialogConfig);

        dialogRef.afterClosed().subscribe(
            data => console.log('Dialog output:', data)
        );
    }

    editProjectDialog(project: Project): void {
        const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;

        dialogConfig.data = project;
        console.log('Update data=>');
        console.log(project);
        const dialogRef = this.dialog.open(ProjectDialogComponent, dialogConfig);

        dialogRef.afterClosed().subscribe(
            data => console.log('Edit dialog output:', data)
        );
    }
}
