import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Task} from '../_models';
import {map} from 'rxjs/operators';

const httpOptions = {
    headers: new HttpHeaders(
        {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + (localStorage.getItem('currentUser') !== null ? JSON.parse(localStorage.getItem('currentUser')).token  : '')
        }
    )
};

@Injectable({
    providedIn: 'root'
})
export class TaskService {

    constructor(private http: HttpClient) {

    }

    private getTaskProjectURL = `/v1api/task/getTasks/project/`;
    private getTaskSprintURL = `/v1api/task/getTasks/sprint/`;
    private getTaskChangeStatusURL = `/v1api/task/changeStatus/`;
    private addTaskURL = `/v1api/task/addTask/`;
    private addTaskToSprintURL = `/v1api/task/addToSprint/`;

    getTaskProject(projectId: string) {
        return this.http.get<any>(this.getTaskProjectURL + projectId)
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

    getTaskSprint(sprintId: string) {
        return this.http.get<any>(this.getTaskSprintURL + sprintId)
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

    addTaskToSprint(tasks: Task[], sprintId: string) {
        return this.http.post<any>(this.addTaskToSprintURL + sprintId, tasks)
            .pipe(map(response => {
                try {
                    if (response.header.code === 200) {
                        return response.body.response;
                    } else {
                        console.log('error');
                        return response;
                    }
                } catch (e) {
                    console.log(e);
                }
            }));
    }

    addTask(task: Task, projectId: string) {
        return this.http.post<any>(this.addTaskURL + projectId, task)
            .pipe(map(response => {
                try {
                    if (response.header.code === 200) {
                        return response.body.response;
                    } else {
                        console.log('error');
                        return response;
                    }
                } catch (e) {
                    console.log(e);
                }
            }));
    }

    changeStatus(task: Task) {
        return this.http.post<any>(this.getTaskChangeStatusURL + task.taskId, {status: task.status})
            .pipe(map(response => {
                try {
                    if (response.header.code === 200) {
                        return response.body.response;
                    } else {
                        console.log('error');
                        return response;
                    }
                } catch (e) {
                    console.log(e);
                }
            }));
    }

}
