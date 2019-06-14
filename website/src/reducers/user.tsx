import * as actions from '../actions/user';
import { UserState } from '../types';

export const initialAppState = {
	isAuthenticated: false,
	isProfileLoaded: false,
	links: [],
};

export const userReducer =
		(state: UserState = initialAppState, action: actions.UserAction): UserState => {
	switch (action.type) {
		case actions.LOGIN_SUCCEEDED:
			return { ...state, isAuthenticated: true };
		case actions.LOGOUT:
			return { ...state, isAuthenticated: false, isProfileLoaded: false, profile: undefined };
		case actions.LOAD_PROFILE_SUCCEEDED:
			return { ...state, ...action.user };
		case actions.LOAD_PROFILE_FAILED:
			return { ...state, isProfileLoaded: false };
		case actions.ADD_LINKS:
			return {...state, links: action.links };
	}
	return state;
};
