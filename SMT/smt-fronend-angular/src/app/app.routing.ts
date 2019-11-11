import {Routes, RouterModule} from '@angular/router';

import {HomeComponent} from './_components/home';
import {LoginComponent} from './_components/login';
import {RegisterComponent} from './_components/register';
import {ProjectComponent} from './_components/project';
import {AuthGuard} from './_helpers';
import {TaskComponent} from './_components/task';
import {SprintComponent} from './_components/sprint/sprint.component';

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'project', component: ProjectComponent, canActivate: [AuthGuard]},
    {path: 'project/:projectId', component: SprintComponent},
    {path: 'sprint/:sprintId', component: TaskComponent},

    // otherwise redirect to home
    {path: '**', redirectTo: ''}
];

export const appRoutingModule = RouterModule.forRoot(routes);
