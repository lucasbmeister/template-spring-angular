import { ThfMenuItem } from "@totvs/thf-ui";

export interface ThfMenuItemWithPermission extends ThfMenuItem {
	permission? : string;

	subItems? : Array<ThfMenuItemWithPermission>;
}