import { Moment } from "moment";

export interface Task {
	id: number;
	name: string;
	description: string;
	imageFile: string;
}

export interface TaskSchedule {
	id: number;
	taskId: number;
	enabled: boolean;
	type: string;
	start: Moment;
	begin: Moment;
	end: Moment;
	intervalInMinute: number;
}

export interface TaskOccurrence {
	id: number;
	taskId: number;
	taskName: string;
	status: string;
	start: string;
	end: string;
}

export interface TaskMessage {
	id: number;
	occurrenceId: number;
	time: string;
	level: string;
	message: string;
}
