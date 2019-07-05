export interface IRoleDto {
	id: number | string
	role: string
}

export class RoleDto implements IRoleDto {
	id: number | string
	role: string

	constructor(values? : any) {
		if(values)
			Object.assign(this, values);
	}

	static fromJson(json: Array<any> = []) {

		let items: Array<RoleDto> = [];

		for (let values of json) {
			items.push(new RoleDto(values));
		}

		return items;
	}

	static of(json: any = {}) {
		return new RoleDto(json);
	}
}