import { UserState, ApiLink } from '../types';

export const LOGIN_SUCCEEDED = "LOGIN_SUCCEEDED";
export const LOGOUT = "LOGOUT";
export const LOAD_PROFILE_SUCCEEDED = "LOAD_PROFILE_SUCCEEDED";
export const LOAD_PROFILE_FAILED = "LOAD_PROFILE_FAILED";
export const ADD_LINKS = "ADD_LINK";

export interface LoginSucceeded {
	type: typeof LOGIN_SUCCEEDED;
}

export interface Logout {
	type: typeof LOGOUT;
}

export interface LoadProfileSucceeded {
	type: typeof LOAD_PROFILE_SUCCEEDED;
	user: UserState;
}

export interface LoadProfileFailed {
	type: typeof LOAD_PROFILE_FAILED;
}

export interface AddLinks {
	type: typeof ADD_LINKS;
	links: ApiLink[];
}

export type UserAction =
	LoginSucceeded
	| Logout
	| LoadProfileSucceeded
	| LoadProfileFailed
	| AddLinks;

export function loginSucceeded(): LoginSucceeded {
	return {
		type: LOGIN_SUCCEEDED,
	};
}

export function logout(): Logout {
	return {
		type: LOGOUT,
	};
}

export function loadProfileSucceeded(user: UserState): LoadProfileSucceeded {
	return {
		type: LOAD_PROFILE_SUCCEEDED,
		user,
	};
}

export function loadProfileFailed(): LoadProfileFailed {
	return {
		type: LOAD_PROFILE_FAILED,
	};
}

export function onAddLinks(links: ApiLink[]): AddLinks {
	return {
		type: ADD_LINKS,
		links,
	};
}
