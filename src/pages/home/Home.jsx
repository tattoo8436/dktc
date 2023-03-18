import classNames from "classnames";
import React, { useEffect, useMemo, useRef, useState } from "react";
import { AgGridReact } from "ag-grid-react";
import "ag-grid-enterprise";
import "ag-grid-community";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";
import { getColumnDefs1 } from "./ColumnDefs1";
import dataSubject from "./fakeData";
import { getColumnDefs2 } from "./ColumnDefs2";
import { Avatar, Button, Popover, Select } from "antd";
import { UserOutlined } from "@ant-design/icons/lib/icons";
import axios from "axios";

const Home = () => {
  const gridRef1 = useRef();
  const gridRef2 = useRef();

  const [listSubjects, setListSubjects] = useState(null);

  useEffect(() => {
    fetchAllSubject();
    console.log(JSON.parse(localStorage.getItem("account")));
  }, []);

  const columnDefs1 = getColumnDefs1(gridRef1);
  const columnDefs2 = getColumnDefs2(gridRef2);

  const axiosClient = axios.create({
    headers: { "Content-Type": "application/json" },
  });

  const defaultColDef1 = useMemo(
    () => ({
      sortable: false,
      resizable: true,
      editable: false,
      flex: 1,
      suppressMenu: true,
    }),
    []
  );

  const defaultColDef2 = useMemo(
    () => ({
      sortable: false,
      resizable: true,
      editable: false,
      flex: 1,
      suppressMenu: true,
    }),
    []
  );

  const getRowId = (params) => params?.data?.id;

  const fetchAllSubject = async () => {
    try {
      const { data } = await axiosClient.post("http://localhost:8080/account", {
        id: 1,
      });
      console.log(data);
      setListSubjects(
        data.listAccountSubjects?.map((subject) => ({
          label: subject.subject.name,
          value: subject.subject.id,
        }))
      );
    } catch (error) {
      console.log(error);
    }
  };

  const menuElement = () => {
    return (
      <div className="right__menu">
        <Button type="text">Đăng xuất</Button>
      </div>
    );
  };

  console.log(listSubjects?.at(0));

  return (
    <div className="home">
      <div className="home__header">
        <div className="home__header__left">Hệ thống đăng ký tín chỉ</div>

        <div className="home__header__right">
          <div className="right__name">Admin</div>
          <Popover content={menuElement()} trigger="click">
            <Avatar className="right__icon" size={32} icon={<UserOutlined />} />
          </Popover>
        </div>
      </div>
      <div className="home__content">
        <div className="home__content__title">Đăng ký giảng dạy</div>

        <div className="home__content__select">
          <div className="select__label">Chọn môn học:</div>
          <Select
            className="select__input"
            defaultValue={1}
            options={listSubjects}
            allowClear={false}
            onChange={(e) => {
              console.log(e);
            }}
          />
        </div>

        <div className="home__content__subject">
          <div
            className={classNames("ag-theme-alpine", {})}
            style={{ width: "100%", height: "300px" }}
          >
            <AgGridReact
              ref={gridRef1}
              rowData={dataSubject()}
              columnDefs={columnDefs1}
              defaultColDef={defaultColDef1}
              animateRows={true}
              onCellClicked={(e) => console.log(e)}
              rowSelection={"multiple"}
              getRowId={getRowId}
            />
          </div>
        </div>

        <div className="home__content__pick">
          <div className="pick__header">
            <div className="pick__header__left">
              <div className="left__title">Xem đăng ký</div>
              <div className="left__total">Tổng tín chỉ: 0</div>
            </div>
            <div className="pick__header__right">
              <Button type="primary">Lưu đăng ký</Button>
            </div>
          </div>
          <div
            className={classNames("ag-theme-alpine", {})}
            style={{ width: "100%", height: "300px" }}
          >
            <AgGridReact
              ref={gridRef2}
              rowData={[]}
              columnDefs={columnDefs2}
              defaultColDef={defaultColDef2}
              animateRows={true}
              //onCellClicked={e => console.log(e)}
              rowSelection={"single"}
              getRowId={getRowId}
              onRowSelected={(e) => console.log(e)}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
