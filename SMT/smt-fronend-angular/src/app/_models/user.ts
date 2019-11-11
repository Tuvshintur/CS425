import {Role} from './role';
import {Project} from './project';
import {StatusChangeRecord} from './statusChangeRecord';

export class User {
    id: number;
    username: string;
    email: string;
    password: string;
    createdAt: Date;
    updatedAt: Date;
    firstName: string;
    lastName: string;
    roles: Role[];
    projects: Project[];
    statusChangeRecords: StatusChangeRecord[];
    token: string;
}
