import * as React from 'react';

import classNames from 'classnames';
import {
	WithStyles, createStyles, ListItem, ListItemIcon, ListItemText,
} from '@material-ui/core';
import { withStyles, Theme } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';

import List from '@material-ui/core/List';
import IconButton from '@material-ui/core/IconButton';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import Divider from '@material-ui/core/Divider';

import HomeIcon from '@material-ui/icons/Home';
import PersonalIcon from '@material-ui/icons/Person';
import MarketIcon from '@material-ui/icons/TrendingUp';
import StatisticsIcon from '@material-ui/icons/Equalizer';
import AnalyticsIcon from '@material-ui/icons/Exposure';
import PreferenceIcon from '@material-ui/icons/Settings';
import ManagerIcon from '@material-ui/icons/Build';

import { Link } from 'react-router-dom';

const drawerWidth = 240;

const styles = (theme: Theme) => createStyles ({
	root: {
		flexGrow: 1,
	},
	grow: {
		flexGrow: 1,
	},
	menuButton: {
		marginLeft: 12,
		marginRight: 36,
	},
	nested: {
		paddingLeft: theme.spacing.unit * 4,
	},
	toolbarIcon: {
		display: 'flex',
		alignItems: 'center',
		justifyContent: 'flex-end',
		padding: '0 8px',
		...theme.mixins.toolbar,
	},
	menuButtonHidden: {
		display: 'none',
	},
	title: {
		flexGrow: 1,
	},
	drawerPaper: {
		flexGrow: 1,
		position: 'relative',
		height: '100vh',
		whiteSpace: 'nowrap',
		width: drawerWidth,
		transition: theme.transitions.create('width', {
			easing: theme.transitions.easing.sharp,
			duration: theme.transitions.duration.enteringScreen,
		}),
		display: 'flex',
	},
	drawerPaperClose: {
		flexGrow: 1,
		height: '100vh',
		vw: 100,
		overflowX: 'hidden',
		transition: theme.transitions.create('width', {
			easing: theme.transitions.easing.sharp,
			duration: theme.transitions.duration.leavingScreen,
		}),
		width: theme.spacing.unit * 7,
		[theme.breakpoints.up('sm')]: {
			width: theme.spacing.unit * 9,
		},
	},
});

export interface Props extends WithStyles<typeof styles> {
	authorities: string[];
}

class AppDrawer extends React.Component<Props, any> {
	constructor(props: Props) {
		super(props);
		this.state = {
			open: true,
		};
	}

	public render() {
		const { classes, authorities } = this.props;

		const HomeLink = (props: any) => <Link {...props} to="/" />;
		const PersonalLink = (props: any) => <Link {...props} to="/personal" />;
		const MarketLink = (props: any) => <Link {...props} to="/market" />;
		const StatisticsLink = (props: any) => <Link {...props} to="/statistics" />;
		const AnalyticsLink = (props: any) => <Link {...props} to="/analytics" />;
		const PreferenceLink = (props: any) => <Link {...props} to="/preference" />;
		const ManagementLink = (props: any) => <Link {...props} to="/manager" />;

		return (
			<Drawer
				variant="permanent"
				classes={{
					paper: classNames(classes.drawerPaper, !this.state.open && classes.drawerPaperClose),
				}}
				open={this.state.open}
			>
				<div className={classes.toolbarIcon}>
					<IconButton onClick={this.handleDrawerClick}>
						{this.state.open && <ChevronLeftIcon />}
						{!this.state.open && <ChevronRightIcon />}
					</IconButton>
				</div>
				<Divider />
				<List>
					<ListItem component={HomeLink} button>
						<ListItemIcon>
							<HomeIcon />
						</ListItemIcon>
						<ListItemText inset primary="主页" />
					</ListItem>
					<Divider />
					<ListItem component={PersonalLink} button>
						<ListItemIcon>
							<PersonalIcon />
						</ListItemIcon>
						<ListItemText inset primary="我的数据" />
					</ListItem>
					<ListItem
						component={MarketLink}
						button
						accessKey="market"
					>
						<ListItemIcon>
							<MarketIcon />
						</ListItemIcon>
						<ListItemText inset primary="行情数据" />
					</ListItem>
					<ListItem
						component={StatisticsLink}
						button
						accessKey="statistics"
					>
						<ListItemIcon>
							<StatisticsIcon />
						</ListItemIcon>
						<ListItemText inset primary="统计数据" />
					</ListItem>
					<ListItem
						component={AnalyticsLink}
						button accessKey="analytics"
					>
						<ListItemIcon>
							<AnalyticsIcon />
						</ListItemIcon>
						<ListItemText inset primary="分析数据" />
					</ListItem>
					<ListItem
						component={PreferenceLink}
						button accessKey="preference"
					>
						<ListItemIcon>
							<PreferenceIcon />
						</ListItemIcon>
						<ListItemText inset primary="用户设置" />
					</ListItem>
					{
						authorities.indexOf('ROLE_ADMIN') > -1 &&
						<ListItem
							component={ManagementLink}
							button accessKey="management"
						>
							<ListItemIcon>
								<ManagerIcon />
							</ListItemIcon>
							<ListItemText inset primary="系统管理" />
						</ListItem>
					}
				</List>
			</Drawer>
		);
	}

	private handleDrawerClick = () => {
		this.setState((state: any) => ({open: !state.open}));
	}
}

export default withStyles(styles)(AppDrawer);
