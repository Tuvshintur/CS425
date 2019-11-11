import {TaskStatus} from './taskStatus';
import {User} from './user';
import {Project} from './project';
import {Sprint} from './sprint';
import {StatusChangeRecord} from './statusChangeRecord';

export class Task {
    taskId: number;
    name: string;
    priority: number;
    developmentEffortPoint: number;
    testEffortPoint: number;
    status: TaskStatus;
    developer: User;
    tester: User;
    startDate: Date;
    dueDate: Date;
    createdUser: User;
    createdDate: Date;
    project: Project;
    sprint: Sprint;
    statusChangeRecords: StatusChangeRecord[];

    constructor() {
        this.name = '';
        this.priority = 0;
        this.developmentEffortPoint = 0;
        this.testEffortPoint = 0;
        this.status = null;
        this.developer = null;
        this.tester = null;
        this.startDate = null;
        this.dueDate = null;
        this.createdUser = null;
        this.createdDate = null;
        this.project = null;
        this.sprint = null;
        this.statusChangeRecords = [];
    }
}
