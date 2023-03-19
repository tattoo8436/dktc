import { Checkbox, Input } from 'antd';
import classNames from 'classnames';
import React, { } from 'react';
import _ from 'lodash';
import { v4 as uuidv4 } from "uuid";
import { Controller } from 'react-hook-form';

export const getColumnDefs1 = (gridRef1, gridRef2, listIds, setListIds, setTotalSTC, control, watch, getValues) => {
  const accountId = JSON.parse(localStorage.getItem("account"))?.id;

  return [
    {
      field: 'id',
      headerName: '',
      maxWidth: 70,
      // checkboxSelection: true,
      // showDisabledCheckboxes: true,
      //valueFormatter: e => '',
      cellClass: (e) =>
        classNames('', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      cellRenderer: e => {
        return <div className='checkbox'>
          <Checkbox
            checked={_.includes(listIds, e.data.id)}
            onChange={() => {
              const arr = listIds;
              arr.push(e.data.id);
              console.log(arr);
              setListIds(arr);
              gridRef1.current.api.redrawRows();

              gridRef2?.current?.api?.applyTransaction({
                add: [{ ...e.data }],
                addIndex: 9999,
              });

              setTotalSTC(pre => pre + e.data.subject.stc);
            }}
          />
        </div>
      }
    },
    {
      field: 'subject.code',
      headerName: 'Mã môn học',
      minWidth: 100,
      cellClass: (e) =>
        classNames('', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => {
        //console.log(e);
        return e.value ?? '-';
      },
    },
    {
      field: 'subject.name',
      headerName: 'Tên môn học',
      minWidth: 280,
      cellClass: (e) =>
        classNames('', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'groupCode',
      headerName: 'Nhóm môn học',
      minWidth: 100,
      cellClass: (e) =>
        classNames('align-right', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'subject.stc',
      headerName: 'Số tín chỉ',
      minWidth: 100,
      cellClass: (e) =>
        classNames('align-right', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'classCode',
      headerName: 'Mã lớp',
      minWidth: 100,
      cellClass: (e) =>
        classNames('', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'quantity',
      headerName: 'Số lượng',
      minWidth: 100,
      cellClass: (e) =>
        classNames('align-right', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'remainQuantity',
      headerName: 'Còn lại',
      minWidth: 100,
      cellClass: (e) =>
        classNames('align-right', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      //valueFormatter: e => e.value ?? '-',
      cellRenderer: e => {
        return <Controller
          name={`remainQuantity${e.data.id}`}
          control={control}
          defaultValue={e.value}
          render={({ field }) => {
            return watch(`remainQuantity${e.data.id}`)
          }}
        />
      }
    },
    {
      field: 'weekdayName',
      headerName: 'Thứ',
      minWidth: 100,
      cellClass: (e) =>
        classNames('', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'lessonStart',
      headerName: 'Tiết bắt đầu',
      minWidth: 100,
      cellClass: (e) =>
        classNames('align-right', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'lessonNumber',
      headerName: 'Số tiết',
      minWidth: 100,
      cellClass: (e) =>
        classNames('align-right', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'room',
      headerName: 'Phòng',
      minWidth: 100,
      cellClass: (e) =>
        classNames('', {
          'cell-disable': watch(`remainQuantity${e.data.id}`) === 0 || _.includes(listIds, e.data.id),
        }),
      valueFormatter: e => e.value ?? '-',
    },
  ]
} 