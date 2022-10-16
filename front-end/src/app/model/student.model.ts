export enum SortType {
  NONE = 'NONE',
  BUBBLE_SORT = 'BUBBLE_SORT',
  HEAP = 'HEAP',
  MERGE = 'MERGE'
}

export class Student {
  name: string;
  performance: number;
}

export class StudentSortResult {
  sortDurationInMs: number;
  numberOfRecords: number;
  sortType: SortType;
  sortedList: Student[];
}

