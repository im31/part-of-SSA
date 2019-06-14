import App from '../../components/app/App';
import * as actions from '../../actions/link';
import {connect} from 'react-redux';
import {Dispatch} from 'redux';
import { ApiLink } from 'src/types';

export function mapDispatchToProps(dispatch: Dispatch<actions.AddLinks>) {
	return {
		onAddLinks: (links: ApiLink[]) => dispatch(actions.onAddLinks(links)),
	};
}

export default connect(null, mapDispatchToProps)(App);
