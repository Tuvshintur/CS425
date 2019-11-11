import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Project } from '../_models';
import { map } from 'rxjs/operators';

const httpOptions = {
    headers: new HttpHeaders(
        {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + (localStorage.getItem('currentUser') !== null ? JSON.parse(localStorage.getItem('currentUser')).token : '')
        }
    )
};

@Injectable()
export class ProjectService {
    constructor(private http: HttpClient) {
    }

    private projectUrl = `/v1api/project/getProjects`;
    private projectSubmitUrl = `/v1api/project/addProject`;
    getAllProjects() {
        return this.http.get<any>(this.projectUrl)
            .pipe(map(response => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                try {
                    if (response.header.code === 200) {
                        return response.body.response.list;
                    } else {
                        console.log('error');
                        return response;
                    }
                } catch (e) {
                    console.log(e);
                }
            }));
    }

    addProject(project: Project) {
        console.log("Srv: add project");
        return this.http.post<Project>(this.projectSubmitUrl, project);
    }
}
