import {Task} from './task';
import {User} from './user';
import {Sprint} from './sprint';
import {ProjectStatus} from './projectStatus';

export class Project {
    projectId: number;
    name: string;
    description: string;
    sprintDuration: number;
    createdDate: Date;
    createdUser: User;
    backLog: Task[];
    members: User[];
    sprints: Sprint[];
    status: boolean;
    scrumMaster: User;
    dueDate: Date;
    startDate: Date;
    projectStatus: ProjectStatus;

    constructor() {
        this.projectId = 0;
        this.name = '';
        this.description = '';
        this.sprintDuration = 0;
        this.createdDate = null;
        this.createdUser = null;
        this.backLog = [];
        this.members = [];
        this.sprints = [];
        this.status = false;
        this.scrumMaster = null;
        this.dueDate = null;
        this.startDate = null;
        this.projectStatus = null;
    }


}
