import Trading from '../../../components/contents/personal/Trading';
import {StoreState, TradingRecord} from '../../../types/index';
import {connect} from 'react-redux';
import * as actions from '../../../actions/personal';
import { Dispatch } from 'redux';
import { Moment } from 'moment';

export function mapStateToProps(state: StoreState) {
	return {
		isAuthenticated: state.user.isAuthenticated,
		trading: state.personal.trading,
		userLinks: state.user.links,
	};
}

export function mapDispatchToProps(dispatch: Dispatch<actions.PersonalAction>) {
	return {
		onLoadTradingSucceeded: (records: TradingRecord[], start: Moment, end: Moment) =>
			dispatch(actions.onLoadTradingSucceeded(records, start, end)),

		onDirectionChanged: (direction: string) => dispatch(actions.onDirectionChanged(direction)),

		changeRowsPerPage: (rowsPerPage: number) =>
		dispatch(actions.changeTradingPage(rowsPerPage)),

		changePage: (page: number) => dispatch(actions.changeTradingPage(page)),
		changeOrder: (order: string) => dispatch(actions.changeTradingOrder(order)),
		changeOrderBy: (orderBy: string) => dispatch(actions.changeTradingOrderBy(orderBy)),
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(Trading);
