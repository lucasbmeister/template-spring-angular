import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable, pipe } from "rxjs";
import { environment } from "src/environments/environment";
import { map } from "rxjs/operators";
import { RoleDto, IRoleDto } from "@shared/dtos/security/roles/role-dto";
import { TotvsResponse } from "@shared/dtos/base/totvs-response.interface";
import { ThfComboFilter, ThfComboOption } from "@totvs/thf-ui";

@Injectable()
export class RoleService implements ThfComboFilter {

	private contextUrl: string = environment.endpoint;
	private api: string = `${this.contextUrl}/roles`

	constructor(private http: HttpClient) { }

	public getRoles(page = "1", pageSize = "10", order = "+role", fields?): Observable<TotvsResponse<RoleDto>> {
		var params: HttpParams = new HttpParams();

		params = params.append("page", page)
			.append("pageSize", pageSize)
			.append("order", order)
		if (fields) {
			params = params.append("fields", fields);
		}

		return this.http.get<TotvsResponse<RoleDto>>(this.api, { params: params })
			.pipe(
				map(response => {
					return new TotvsResponse<RoleDto>(RoleDto.fromJson(response.items), response.hasNext);
				})
			);
	}

	public getRoleById(id: string | number): Observable<RoleDto> {
		return this.http.get<RoleDto>(`${this.api}/${id}`)
			.pipe(
				map(role => RoleDto.of(role))
			);
	}

	getFilteredData(params: any, filterParams?: any): Observable<ThfComboOption[]> {

		return this.http
			.get(this.api, { params })
			.pipe(map((response: any) => {
				return this.convertToArrayComboOption(response.items);
			}));
	}
	getObjectByValue(value: string | number, filterParams?: any): Observable<ThfComboOption> {
		return this.http
			.get(`${this.api}/${value}`)
			.pipe(map(item => this.convertToThfComboOption(item)));
	}

	private convertToThfComboOption(item): ThfComboOption {
		item = item || {};

		return {
			value: item['id'] || undefined,
			label: item['role'] || undefined
		};
	}

	private convertToArrayComboOption(items: Array<any>): Array<ThfComboOption> {

		if (items && items.length > 0) {
			return items.map(item => this.convertToThfComboOption(item));
		}

		return [];
	}
}