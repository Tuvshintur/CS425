import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Sprint} from '../_models';

@Injectable({
    providedIn: 'root'
})
export class SprintService {

    constructor(private http: HttpClient) {
    }

    private sprintSubmitUrl = '/v1api/sprint/';
    private sprintUrl = '/v1api/sprint/';
    private sprintDetailUrl = '/v1api/sprint/detail/';

    getAll(projectId: string) {
        return this.http.get<any>(this.sprintUrl + projectId + '/getSprints')
            .pipe(map(response => {
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
                }
            ));
    }

    addSprint(sprint: Sprint, projectId: string) {
        return this.http.post<any>(this.sprintSubmitUrl + projectId + '/add', sprint)
            .pipe(map(response => {
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
                }
            ));
    }

    getSprintDetail(sprintId: string) {
        return this.http.get<any>(this.sprintDetailUrl + sprintId)
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
                }
            ));
    }
}
