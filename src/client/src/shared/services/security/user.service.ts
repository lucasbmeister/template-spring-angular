import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable, pipe } from "rxjs";
import { environment } from "src/environments/environment";
import { UserDto } from "@shared/dtos/security/users/user-dto";
import { map } from "rxjs/operators";
import { TotvsResponse } from "@shared/dtos/base/totvs-response.interface";
import * as _ from "lodash";

@Injectable()
export class UserService {

	private contextUrl : string = environment.endpoint;
	private api : string = `${this.contextUrl}/users`

	constructor(private http : HttpClient) {}

	public getUsers(page = "1", pageSize = "10",order = "+username", fields?) : Observable<TotvsResponse<UserDto>> {
		var params : HttpParams = new HttpParams();

		params = params.append("page", page)
					   .append("pageSize", pageSize)
					   .append("order", order)
		if (fields) {
			params = params.append("fields", fields);
		}		   

		return this.http.get<TotvsResponse<UserDto>>(this.api,{params : params})
						.pipe(
							map(response => {
								return new TotvsResponse<UserDto>(UserDto.fromJson(response.items), response.hasNext)
							})
						)
	}

	public getUserById(id : string | number) : Observable<UserDto> {
		return this.http.get<UserDto>(`${this.api}/${id}`)
						.pipe(
							map(user => UserDto.of(user))
						);
	}

	public deleteUser(id : string | number) : Observable<boolean> {
		return this.http.delete<boolean>(`${this.api}/${id}`);
	}

	public updateUser(user : UserDto) : Observable<UserDto> {
		return this.http.put<UserDto>(`${this.api}`, user)
	}

	public createUser(user : UserDto) : Observable<UserDto> {

		var userClone : UserDto = _.cloneDeep(user);

		userClone.setPassword(userClone.passwordToSHA3());

		return this.http.post(this.api, userClone)
						.pipe(
							map(response => UserDto.of(response))
						)
	}
}