import DateTimeFormat = Intl.DateTimeFormat;
import {Task} from './task';
import {Project} from './project';
import {SprintStatus} from './sprintStatus';
import {SprintPhase} from './sprintPhase';

export class Sprint {
    sprintId: number;
    name: string;
    description: string;
    sprintPhase: SprintPhase;
    effortPlanned: number;
    effortBurnt: number;
    sprintStatus: SprintStatus;
    startDate: Date;
    endDate: Date;
    project: Project;
    tasks: Task[];


  constructor() {
    this.name = '';
    this.description = '';
    this.sprintPhase = 0;
    this.effortPlanned = 0;
    this.effortBurnt = 0;
    this.sprintStatus = 0;
    this.startDate = null;
    this.endDate = null;
    this.project = null;
    this.tasks = null;
  }
}
