import React, { FC, useEffect, useState } from 'react';
import { BarChart, Bar, Cell, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import axios from 'axios';

interface Props {
  startDate: Date;
  endDate: Date;
}

const getDateMonth = month => {
  axios.get<any>('/api/monthName').then(response => {
    console.log(response);
    month(response);
  });
};

const getDateQuarter = quarter => {
  axios.get<any>(`/api/admin/quarterName`).then(response => {
    console.log(response);
    quarter(response);
  });
};

export const data = [
  {
    date: '2022-11-11',
    yandex: 4000,
    mail: 2400,
    amt: 2400,
  },
  {
    date: '2022-12-27',
    yandex: 3000,
    mail: 1398,
    amt: 2210,
  },
  {
    date: '2022-12-28',
    yandex: 2000,
    mail: 9800,
    amt: 2290,
  },
  {
    date: '2022-12-29',
    yandex: 2780,
    mail: 3908,
    amt: 2000,
  },
  {
    date: '2022-12-30',
    yandex: 1890,
    mail: 4800,
    amt: 2181,
  },
  {
    date: '2023-01-27',
    yandex: 2390,
    mail: 3800,
    amt: 2500,
  },
  {
    date: '2023-01-29',
    yandex: 3490,
    mail: 4300,
    amt: 2100,
  },
];

const isInDateInterval = (start: Date, end: Date, myDate: Date): boolean => myDate > start && myDate < end;

export const Graf1: FC<Props> = props => {
  const onClickG = () => window.open('https://yandex.ru');
  const onClickYa = () => window.open(' https://mail.ru/');
  const dataInterval = data.filter(d => isInDateInterval(props.startDate, props.endDate, new Date(d.date)));
  return (
    <BarChart
      width={500}
      height={300}
      data={dataInterval}
      margin={{
        top: 20,
        right: 30,
        left: 20,
        bottom: 5,
      }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="date" />
      <YAxis />
      <YAxis />
      <Legend />
      <Tooltip />
      <Bar dataKey="yandex" stackId="a" fill="#8884d8" onClick={onClickG} />
      <Bar dataKey="mail" stackId="a" fill="#82ca9d" onClick={onClickYa} />
    </BarChart>
  );
};

export default Graf1;
