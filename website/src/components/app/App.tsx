import * as React from 'react';
import './App.css';
import AppHeader from '../../containers/header/AppHeader';
import { getCurrentUser, doGetRequest } from '../../api';
import { ROOT_LINK } from '../../constants';
import Main from '../contents/main/Main';
import { createMuiTheme, CssBaseline, MuiThemeProvider } from '@material-ui/core';
import { parseLinks } from 'src/api/utilities';
import { ApiLink } from 'src/types';

const theme = createMuiTheme({
	palette: {
		type: 'light',
	},
	typography: {
		useNextVariants: true,
	},
});

export interface Props {
	onAddLinks: (links: ApiLink[]) => void;
	history: any;
}

class App extends React.Component<Props> {
	constructor(props: Props) {
		super(props);
	}

	public componentDidMount() {
		this.loadRootLinks();
	}

	public render() {
		return (
			<React.Fragment>
				<CssBaseline />
				<MuiThemeProvider theme={theme}>
					<div className="root">
						<div className="header">
							<AppHeader />
						</div>
						<div className="main">
							<div className="content">
								<Main />
							</div>
						</div>
					</div>
				</MuiThemeProvider>
			</React.Fragment>
		);
	}

	private loadRootLinks() {
		doGetRequest(ROOT_LINK).then((response) => {
			const linksMap = parseLinks(response);
			const links: ApiLink[] = [];
			linksMap.forEach((value: string, key: string) => {
				links.push({rel: key, href: value});
			});
			this.props.onAddLinks(links);
		});
	}
}

export default App;
