import * as React from 'react';
import * as ReactDOM from 'react-dom';
import registerServiceWorker from './registerServiceWorker';
import {createStore} from 'redux';
import staApp from './reducers';
import {Provider} from 'react-redux';
import App from './containers/app/App';
import {BrowserRouter as Router, Route } from 'react-router-dom';
// tslint:disable-next-line:no-implicit-dependencies
import {devToolsEnhancer} from 'redux-devtools-extension';

const store = createStore(staApp, devToolsEnhancer({}));

const router =
		<Provider store={store}>
			<Router>
				<Route path="/" component={App}/>
			</Router>
		</Provider>;

ReactDOM.render(
		router,
		document.getElementById('root') as HTMLElement,
);

registerServiceWorker();
