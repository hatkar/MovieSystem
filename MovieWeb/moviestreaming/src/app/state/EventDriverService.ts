import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import { NotificationEvent } from '../models/state';
@Injectable({providedIn:"root"})
export class EventDriverService {

    sourceEventSubject:Subject<NotificationEvent> = new Subject<NotificationEvent>();
    sourceEventSubjectObservable=this.sourceEventSubject.asObservable();
    publishEvent(event:NotificationEvent){
        this.sourceEventSubject.next(event);
    }
}