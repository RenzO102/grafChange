import React, { FC, useEffect, useState } from 'react';
import { BarChart, Bar, Cell, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import axios from 'axios';
import { RangeType } from 'app/modules/home/dataPick';

interface Props {
  startDate: Date;
  endDate: Date;
  dateRange: {};
  rangeType: RangeType;
}

export const Graf1: FC<Props> = props => {
  const onClickYandex = () => window.open('https://yandex.ru');

  const isInDateInterval = (start: Date, end: Date, myDate: string): boolean => {
    const currStep = +myDate;

    let startBorder, endBorder: number;

    switch (props.rangeType) {
      case 'weeks':
        const getWeekNumber = (date: Date) => Math.ceil(((date.getTime() - onejan.getTime()) / 86400000 + onejan.getDay() + 1) / 7);
        const onejan = new Date(2022, 0, 1);
        startBorder = getWeekNumber(start);
        endBorder = getWeekNumber(end);
        break;
      case 'months':
        startBorder = props.startDate.getMonth() + 1;
        endBorder = props.endDate.getMonth() + 1;
        break;
      case 'quarters':
        const getQuarterNumber = (date: Date) => Math.floor((date.getMonth() + 3) / 3);
        startBorder = getQuarterNumber(start);
        endBorder = getQuarterNumber(end);
        break;
    }
    return currStep >= startBorder && currStep <= endBorder;
  };

  const convertToArray = data => {
    const dateArray = Object.keys(data).map(key => ({ date: key, people: data[key] }));
    return dateArray.filter(d => isInDateInterval(props.startDate, props.endDate, d.date));
  };

  return (
    <BarChart
      width={1000}
      height={450}
      data={convertToArray(props.dateRange)}
      margin={{
        top: 20,
        right: 30,
        left: 20,
        bottom: 5,
      }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="date" minTickGap={-200} />
      <YAxis />
      <YAxis />
      <Legend />
      <Tooltip />
      <Bar dataKey="people" stackId="a" fill="#8884d8" onClick={onClickYandex} />
    </BarChart>
  );
};

export default Graf1;
