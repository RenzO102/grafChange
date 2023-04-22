import React, { FC, useState } from 'react';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

interface Props {
  startDate: Date;
  pickStartDate: (date: Date) => void;
  endDate: Date;
  pickEndDate: (date: Date) => void;
  pickDateRange: (range: any) => void;
  pickRangeType: (type: RangeType) => void;
}

export type RangeType = 'weeks' | 'months' | 'quarters';

const requestDateRange = async (rangeType: RangeType) => {
  const response = await axios.get<any>(`/api/${rangeType}`);
  return response.data;
};

export const TableDatePicker: FC<Props> = props => {
  const changeStartDate = (isAdd: boolean) => {
    const newDate = new Date(props.startDate);
    newDate.setDate(newDate.getDate() + (isAdd ? 30 : -30));
    if (newDate > props.endDate) return;
    props.pickStartDate(newDate);
  };

  const changeEndDate = (isAdd: boolean) => {
    const newDate = new Date(props.endDate);
    newDate.setDate(newDate.getDate() + (isAdd ? 30 : -30));
    if (newDate < props.startDate) return;
    props.pickEndDate(newDate);
  };

  const getDateRange = async (rangeType: 'weeks' | 'months' | 'quarters') => {
    const data = await requestDateRange(rangeType);
    props.pickRangeType(rangeType);
    props.pickDateRange(data);
    console.log(Object.entries(data));
  };

  return (
    <div style={{ display: 'inline-grid' }}>
      <div style={{ marginBottom: 20, display: 'inline-flex' }}>
        <button onClick={() => changeStartDate(false)}> {'<'} </button>
        <DatePicker
          selected={props.startDate}
          selectsStart
          startDate={props.startDate}
          endDate={props.endDate}
          onSelect={props.pickStartDate}
        />
        <button onClick={() => changeStartDate(true)}> {'>'} </button>
      </div>

      <div style={{ marginBottom: 20, display: 'inline-flex' }}>
        <button onClick={() => changeEndDate(false)}> {'<'} </button>
        <DatePicker
          selected={props.endDate}
          selectsEnd
          startDate={props.startDate}
          endDate={props.endDate}
          minDate={props.startDate}
          onSelect={props.pickEndDate}
        />

        <button onClick={() => changeEndDate(true)}> {'>'} </button>
      </div>

      <button onClick={() => getDateRange('weeks')}> week </button>
      <button onClick={() => getDateRange('months')}> months </button>
      <button onClick={() => getDateRange('quarters')}> quarters </button>
    </div>
  );
};

export default TableDatePicker;
