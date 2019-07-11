import { RoleDto } from "@shared/dtos/security/roles/role-dto";
import { SHA3 } from 'sha3';

export class UserDto {

	public id: number;
	public mail?: string;
	public name?: string;
	public password: string;
	public roles?: RoleDto[];
	public surname?: string;
	public username: string;

	public token: string;
	
	constructor(
		id?: number | null,
		username?: string | null,
		token?: string | null,
		roles?: RoleDto[] | null
	) {
		this.id = id;
		this.username = username;
		this.token = token;
		this.roles = roles;
	}

	static init(values: any) {
		var user : UserDto = new UserDto();
		return Object.assign(user, values)
	}

	static fromJson(json: Array<any> = []) {

		let items: Array<UserDto> = [];

		for (let values of json) {
			items.push(this.init(values));
		}

		return items;
	}

	static of(json: any = {}) {
		return this.init(json);
	}

	isAdmin(): boolean {
		if (!this.roles)
			return false;
		return this.roles.find(r => r.role === 'ADMIN') !== undefined;
	}

    /**
     * Check if logged user has one role
     */
	hasRole(role: string | Array<string>): boolean {
		if (!this.roles) return false;

		if (typeof role === 'string') {
			return (this.roles.find(r => r.role === role) !== undefined);
		} else if(typeof role === 'undefined') {
			return true;
		} else {
			const found = role.filter(rol => {
				return (this.roles.find(r => r.role == rol) !== undefined);
			});
			return found.length > 0;
		}
	}

	passwordToSHA3() : string{
		const hash = new SHA3(512);
        hash.update(this.password);
        return hash.digest('hex');
	}

	getUsername() {
		return this.username;
	}

	setUsername(username: string) {
		this.username = username;
	}

	getPassword() {
		return this.password;
	}

	setPassword(password: string) {
		this.password = password;
	}

	getId() {
		return this.id;
	}

	setId(name: string) {
		this.name = name;
	}

	getName() {
		return this.name;
	}

	setName(id: number) {
		this.id = id;
	}

	getToken() {
		return this.token;
	}

	setToken(token: string) {
		this.token = token;
	}

	setRoles(roles: RoleDto[]) {
		this.roles = roles;
	}

	getRoles() {
		return this.roles;
	}
}