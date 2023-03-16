import React, { FC, useState } from 'react';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

interface Props {
  startDate: Date;
  pickStartDate: (date: Date) => void;
  endDate: Date;
  pickEndDate: (date: Date) => void;
}

const getDateWeek = async () => {
  const response = await axios.get<any>('/api/Week');
  return response.data;
};

const getDateMonth = () => {
  axios.get<any>('/api/Month').then(response => {
    console.log(response);
  });
};

const getDateQuarter = () => {
  axios.get<any>(`/api/Quarter`).then(response => {
    console.log(response);
  });
};

export const TableDatePicker: FC<Props> = props => {
  const [weeks, setWeeks] = useState({ '35': 'yarik' });

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

  const getWeeks = async () => {
    const data = await getDateWeek();
    setWeeks(data);
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
          dateFormat={'dd.MM.yy'}
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
          dateFormat={'dd.MM.yy'}
        />

        <button onClick={() => changeEndDate(true)}> {'>'} </button>
      </div>
      <div>{weeks?.['35']}</div>
      <button onClick={getWeeks}> week </button>
    </div>
  );
};

export default TableDatePicker;
