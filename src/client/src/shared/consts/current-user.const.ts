import { BehaviorSubject, Observable } from 'rxjs';
import { UserDto } from '@shared/dtos/security/users/user-dto';
import * as _ from 'lodash';

/**
 * Store the current user
 */
class CurrentUser {
    private currentUserSubject = new BehaviorSubject(null);
    public currentUser$: Observable<UserDto> = this.currentUserSubject.asObservable();

    setUser(user: UserDto) {
        this.currentUserSubject.next(_.cloneDeep(user));
    }
}

export const store = new CurrentUser();