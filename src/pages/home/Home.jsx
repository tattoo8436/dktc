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
import { Avatar, Button, Input, Modal, Popover, Select } from "antd";
import { UserOutlined } from "@ant-design/icons/lib/icons";
import axios from "axios";
import { QuestionCircleOutlined } from "@ant-design/icons";
import { v4 as uuidv4 } from "uuid";
import { Controller, useForm } from "react-hook-form";
import _ from "lodash";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const gridRef1 = useRef();
  const gridRef2 = useRef();
  //let listIds = useRef([]);
  const navigate = useNavigate();

  const [account, setAccount] = useState(null);
  const [subject, setSubject] = useState(1);
  const [listSubjects, setListSubjects] = useState(null);
  const [listLayerClassSubjects, setListLayerClassSubjects] = useState([]);
  const [listLayerClassAccounts, setListLayerClassAccounts] = useState([]);
  const [openModalDelete, setOpenModalDelete] = useState(false);
  const [openModalSave, setOpenModalSave] = useState(false);
  const [listIds, setListIds] = useState(null);
  const [loading, setLoading] = useState(false);
  const [totalSTC, setTotalSTC] = useState(0);
  const [isRefetch, setIsRefetch] = useState(false);

  useEffect(() => {
    const dataAccount = JSON.parse(localStorage.getItem("account"));
    setAccount(dataAccount);
    setListSubjects(
      dataAccount.listAccountSubjects?.map((subject) => ({
        label: subject.subject.name,
        value: subject.subject.id,
      }))
    );
  }, []);

  useEffect(() => {
    fetchLayerClassByAccount();
  }, [isRefetch]);

  useEffect(() => {
    fetchLayerClassBySubject();
  }, [subject, isRefetch]);

  const {
    control,
    watch,
    getValues,
    setValue,
    reset,
    formState: { errors },
  } = useForm({
    mode: "onChange",
  });

  const columnDefs1 = getColumnDefs1(
    gridRef1,
    gridRef2,
    listIds,
    setListIds,
    setTotalSTC,
    control,
    watch,
    getValues
  );
  const columnDefs2 = getColumnDefs2(gridRef2, setOpenModalDelete, watch);

  const axiosClient = axios.create({
    headers: { "Content-Type": "application/json" },
  });

  const defaultColDef1 = useMemo(
    () => ({
      sortable: true,
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

  const fetchLayerClassBySubject = async () => {
    // gridRef1?.current?.api?.redrawRows();
    // reset();
    try {
      const payload = {
        username: JSON.parse(localStorage.getItem("account"))?.username,
        password: JSON.parse(localStorage.getItem("account"))?.password,
        subjectId: subject,
      };
      console.log(payload);
      const { data } = await axiosClient.post(
        "http://26.192.253.140:8080/layer-class/search-by-subjectId",
        payload
      );
      console.log(data);
      setListLayerClassSubjects(data.listLayerClasses);
      setTimeout(() => {
        gridRef1?.current?.api?.redrawRows();
        gridRef2?.current?.api?.redrawRows();
      }, 100);
    } catch (error) {
      console.log(error);
    }
  };

  const fetchLayerClassByAccount = async () => {
    try {
      const payload = {
        username: JSON.parse(localStorage.getItem("account"))?.username,
        password: JSON.parse(localStorage.getItem("account"))?.password,
      };
      console.log(payload);
      const { data } = await axiosClient.post(
        "http://26.192.253.140:8080/layer-class/search-by-accountId",
        payload
      );
      console.log(data);
      setListLayerClassAccounts(data.listLayerClasses);
      setListIds(data.listLayerClasses?.map((i) => i.id));
      setTotalSTC(
        _.reduce(data.listLayerClasses, (sum, n) => sum + n.subject.stc, 0)
      );
    } catch (error) {
      console.log(error);
    }
  };

  const onSubmit = async () => {
    if (totalSTC < 12) {
      toast.error("Chưa đủ số tín chỉ tối thiểu!", { autoClose: 3000 });
      setOpenModalSave(false);
    } else {
      setLoading(true);
      try {
        const payload = {
          username: JSON.parse(localStorage.getItem("account"))?.username,
          password: JSON.parse(localStorage.getItem("account"))?.password,
          listLayerClassIds: listIds,
        };
        console.log(payload);
        const { data } = await axiosClient.post(
          "http://26.192.253.140:8080/layer-class/save",
          payload
        );
        setIsRefetch((pre) => !pre);
        toast.success("Lưu đăng ký thành công!", { autoClose: 3000 });
        setOpenModalSave(false);
        // window.location.reload();
      } catch (error) {
        setOpenModalSave(false);
        console.log(error);
      }
    }
  };

  const getRowId = (params) => params?.data?.id;

  const menuElement = () => {
    return (
      <div className="right__menu">
        <Button type="text" onClick={handleLogout}>
          Đăng xuất
        </Button>
      </div>
    );
  };

  const handleCancelDelete = () => {
    setOpenModalDelete(false);
  };

  const handleCancelSave = () => {
    setOpenModalSave(false);
  };

  const handleDeleteRow = () => {
    const row = gridRef2.current.api.getDisplayedRowAtIndex(
      gridRef2.current.api.getFocusedCell().rowIndex
    );
    console.log(row);
    _.pull(listIds, row.data.id);
    setValue(`remainQuantity${row.data.id}`, 1);
    gridRef1.current.api.redrawRows();
    gridRef2.current.api.applyTransaction({
      remove: [row],
    });
    setTotalSTC((pre) => pre - row.data.subject.stc);
    setOpenModalDelete(false);
  };

  const handleLogout = () => {
    localStorage.removeItem("account");
    navigate("/login");
    window.location.reload();
  };

  console.log("account:", account);
  console.log("rows:", gridRef1?.current?.api?.getSelectedRows());
  console.log("listIds:", listIds);
  console.log("values:", getValues());

  return (
    <div className="home">
      <div className="home__header">
        <div className="home__header__left">Hệ thống đăng ký tín chỉ</div>

        <div className="home__header__right">
          <div className="right__name">
            {JSON.parse(localStorage.getItem("account"))?.name ?? ""}
          </div>
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
            defaultValue={subject}
            options={listSubjects}
            allowClear={false}
            onChange={(e) => {
              console.log(e);
              setSubject(e);
            }}
          />
        </div>

        <div className="home__content__subject">
          <div
            className={classNames("ag-theme-alpine subject__content", {})}
            style={{ width: "100%", height: "300px" }}
          >
            <AgGridReact
              ref={gridRef1}
              rowData={listLayerClassSubjects}
              columnDefs={columnDefs1}
              defaultColDef={defaultColDef1}
              animateRows={true}
              onCellClicked={(e) => console.log(e)}
              rowSelection={"multiple"}
              getRowId={getRowId}
              suppressRowClickSelection={true}
              localeText={{ noRowsToShow: "Không có dữ liệu" }}
              //onCellValueChanged={(e) => console.log(e)}
              // onSelectionChanged={(e) =>
              //   handleSelectRow(e.api.getSelectedNodes())
              // }
              // onFirstDataRendered={(e) => {
              //   e.api.forEachNode((node) =>
              //     node.setSelected(
              //       !!node.data &&
              //         node.data.accountId ===
              //           JSON.parse(localStorage.getItem("account"))?.id
              //     )
              //   );
              // }}
            />
          </div>
        </div>

        <div className="home__content__pick">
          <div className="pick__header">
            <div className="pick__header__left">
              <div className="left__title">Xem đăng ký</div>
              <div className="left__total">Tổng tín chỉ: {totalSTC}</div>
            </div>
            <div className="pick__header__right">
              <Button type="primary" onClick={() => setOpenModalSave(true)}>
                Lưu đăng ký
              </Button>
            </div>
          </div>

          <div
            className={classNames("ag-theme-alpine pick__content", {})}
            style={{ width: "100%", height: "300px" }}
          >
            <AgGridReact
              ref={gridRef2}
              rowData={listLayerClassAccounts}
              columnDefs={columnDefs2}
              defaultColDef={defaultColDef2}
              animateRows={true}
              onCellClicked={(e) => console.log(e)}
              rowSelection={"single"}
              getRowId={getRowId}
              //onRowSelected={(e) => console.log(e)}
            />
          </div>
        </div>
      </div>

      <Modal
        open={openModalDelete}
        centered
        onCancel={handleCancelDelete}
        onOk={handleDeleteRow}
        className="modal-delete"
      >
        <div className="modal-delete__icon">
          <QuestionCircleOutlined />
        </div>
        <div className="modal-delete__content">
          Bạn có chắc muốn xoá lớp học phần này không?
        </div>
      </Modal>

      <Modal
        open={openModalSave}
        centered
        onCancel={handleCancelSave}
        onOk={onSubmit}
        className="modal-save"
      >
        <div className="modal-save__icon">
          <QuestionCircleOutlined />
        </div>
        <div className="modal-save__content">Bạn có chắc muốn lưu đăng ký?</div>
      </Modal>
    </div>
  );
};

export default Home;
