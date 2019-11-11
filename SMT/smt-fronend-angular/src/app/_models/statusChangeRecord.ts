import {TaskStatus} from './taskStatus';
import {User} from './user';
import {Task} from './task';

export class StatusChangeRecord {
  id: number;
  task: Task;
  oldStatus: TaskStatus;
  newStatus: TaskStatus;
  date: Date;
  user: User;
}
