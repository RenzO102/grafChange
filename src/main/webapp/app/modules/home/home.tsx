import './home.scss';
import React, { useState } from 'react';
import { Row, Col } from 'reactstrap';
import GraphicPainter from 'app/modules/home/GraphicPainter';
import TableDatePicker, { RangeType } from 'app/modules/home/dataPick';

export const Home = () => {
  const now = new Date();
  const before = new Date();
  before.setFullYear(before.getFullYear() - 1);
  const [startDate, setStartDate] = useState(new Date(before));
  const [endDate, setEndDate] = useState(new Date(now));
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
        <GraphicPainter startDate={startDate} endDate={endDate} dateRange={dateRange} rangeType={rangeType} />
      </Col>
    </Row>
  );
};

export default Home;
