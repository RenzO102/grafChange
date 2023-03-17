import './home.scss';
import React, { useState } from 'react';
import { Row, Col } from 'reactstrap';
import Graf1 from 'app/modules/home/Graf1';
import TableDatePicker, { RangeType } from 'app/modules/home/dataPick';

export const Home = () => {
  const [startDate, setStartDate] = useState(new Date('2022-01-01'));
  const [endDate, setEndDate] = useState(new Date('2022-12-31'));
  const [dateRange, setDateRange] = useState({});
  const [rangeType, setRangeType] = useState('weeks' as RangeType);

  return (
    <Row>
      <Col>
        <TableDatePicker
          startDate={startDate}
          endDate={endDate}
          pickStartDate={setStartDate}
          pickEndDate={setEndDate}
          pickDateRange={setDateRange}
          pickRangeType={setRangeType}
        />
      </Col>
      <Col>
        <Graf1 startDate={startDate} endDate={endDate} dateRange={dateRange} rangeType={rangeType} />
      </Col>
    </Row>
  );
};

export default Home;
