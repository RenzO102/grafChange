import './home.scss';
import TableDatePicker from 'app/modules/home/dataPick';
import React, { useState } from 'react';
import { Row, Col } from 'reactstrap';
import Graf1, { data } from 'app/modules/home/Graf1';

export const Home = () => {
  const [startDate, setStartDate] = useState(new Date(data[0].date));
  const [endDate, setEndDate] = useState(new Date(data.slice(-1)[0].date));

  return (
    <Row>
      <Col>
        <TableDatePicker startDate={startDate} endDate={endDate} pickStartDate={setStartDate} pickEndDate={setEndDate} />
      </Col>
      <Col>
        <Graf1 startDate={startDate} endDate={endDate} />
      </Col>
    </Row>
  );
};

export default Home;
