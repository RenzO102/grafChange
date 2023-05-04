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
  const isInDateInterval = (start: Date, end: Date, number: string, year: string): boolean => {
    const currYear = +year;
    const diffYear = currYear - start.getFullYear();
    const currStep = +number + diffYear * 52;

    let startBorder, endBorder: number;

    switch (props.rangeType) {
      case 'weeks':
        const getWeekNumber = (date: Date) =>
          Math.ceil(((date.getTime() - onejan(date).getTime()) / 86400000 + onejan(date).getDay() + 1) / 7);
        const onejan = (date: Date) => new Date(start.getFullYear(), 0, 1);
        startBorder = getWeekNumber(start);
        endBorder = getWeekNumber(end);
        console.log(startBorder, endBorder, start.getFullYear(), end.getFullYear(), currYear, currStep);
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

    // weekNumber1 =19 , year = 2022 , weekNumber2= 71 , year = 2023, currStep= 55, currYear=2023
  };

  const convertToArray = data => {
    const dateArray = Object.keys(data).map(key => ({
      date: data[key].weekNumber,
      value: data[key].value,
      yearDate: data[key].year,
      XAxisKey: `${data[key].weekNumber}/${data[key].year}`,
    }));
    console.log(dateArray);
    return dateArray.filter(d => isInDateInterval(props.startDate, props.endDate, d.date, d.yearDate));
  };

  return (
    <BarChart
      width={1500}
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
      <XAxis dataKey="XAxisKey" minTickGap={-20} />
      <YAxis />
      <YAxis />
      <Legend />
      <Tooltip />
      <Bar dataKey="value" stackId="a" fill="#8884d8" onClick={onClickYandex} barSize={1000} maxBarSize={100} />
    </BarChart>
  );
};

export default Graf1;
