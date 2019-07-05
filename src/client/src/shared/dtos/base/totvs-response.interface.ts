export class TotvsResponse<T> {
	items : T[];
	hasNext?: boolean;

	constructor(items : T[], hasNext : boolean){
		this.items = items;
		this.hasNext = hasNext;
	}
}

