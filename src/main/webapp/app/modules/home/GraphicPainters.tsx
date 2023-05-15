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
  const isInDateInterval = (start: Date, end: Date, number: string, year: string): any => {
    const currYear = +year;
    const diffYear = currYear - start.getFullYear();
    const currStepWeek = +number + diffYear * 52;
    const currStepMonth = +number + diffYear * 12;
    const currStepQuarter = +number + diffYear * 4;

    let startBorder, endBorder: number;

    switch (props.rangeType) {
      case 'weeks':
        const getWeekNumber = (date: Date) =>
          Math.ceil(((date.getTime() - oneJan(date).getTime()) / 86400000 + oneJan(date).getDay() + 1) / 7);
        const oneJan = (date: Date) => new Date(start.getFullYear(), 0, 1);
        startBorder = getWeekNumber(start);
        endBorder = getWeekNumber(end);
        console.log(startBorder, endBorder, currStepWeek);
        return currStepWeek >= startBorder && currStepWeek <= endBorder;
        break;
      case 'months':
        const getMonthNumber = (date: Date) =>
          Math.ceil(((date.getTime() - oneJan1(date).getTime()) / 86400000 + oneJan1(date).getMonth() + 1) / 30);
        const oneJan1 = (date: Date) => new Date(start.getFullYear(), 0, 1);
        startBorder = getMonthNumber(start);
        endBorder = getMonthNumber(end);
        console.log(startBorder, endBorder, currStepMonth);
        return currStepMonth >= startBorder && currStepMonth <= endBorder;
        break;
      case 'quarters':
        const getQuarterNumber = (date: Date) =>
          Math.ceil(((date.getTime() - oneJan2(date).getTime()) / 86400000 + oneJan2(date).getDay() + 1) / 90);
        const oneJan2 = (date: Date) => new Date(start.getFullYear(), 0, 1);
        startBorder = getQuarterNumber(start);
        endBorder = getQuarterNumber(end);
        console.log(startBorder, endBorder, currStepQuarter);
        return currStepQuarter >= startBorder && currStepQuarter <= endBorder;
        break;
    }
    // return return currStepWeek >= startBorder && currStepWeek <= endBorder;
    // weekNumber1 =19 , year = 2022 , weekNumber2= 71 , year = 2023, currStep= 55, currYear=2023
  };

  const convertToArray = data => {
    const dateArray = Object.keys(data).map(key => ({
      date: data[key].number,
      value: data[key].value,
      yearDate: data[key].year,
      XAxisKey: `${data[key].number}/${data[key].year}`,
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
