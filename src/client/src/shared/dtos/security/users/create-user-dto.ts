import { RoleDto } from "@shared/dtos/security/roles/role-dto";

export class CreateUserDto {

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

	init(data?: any) {
        if (data) {
            this.username = data["username"];
            this.name = data["name"];
            this.surname = data["surname"];
			this.password = data["password"];
            this.mail = data["mail"];
            if (data["roles"] && data["roles"].constructor === Array) {
                this.roles = [];
                for (let item of data["roles"])
                    this.roles.push(item);
            }
            this.id = data["id"];
        }
    }

    static fromJS(data: any): CreateUserDto {
        data = typeof data === 'object' ? data : {};
        let result = new CreateUserDto();
        result.init(data);
        return result;
    }

	getUsername(){
		return this.username;
	}

	setUsername(username : string) {
		this.username = username;
	}

	getPassword() {
		return this.password;
	}

	setPassword(password : string) {
		this.password = password;
	}

	getId() {
		return this.id;
	}

	public setId(id : number) {
		this.id = id;
	}

	getToken() {
		return this.token;
	}

	setToken(token : string) {
		this.token = token;
	}

	setRoles(roles : RoleDto[]) {
		this.roles = roles;
	}
	
	getRoles() {
		return this.roles;
	}
}