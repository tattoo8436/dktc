import React, { } from 'react';

export const getColumnDefs1 = (gridRef) => {

  return [
    {
      field: 'id',
      headerName: '',
      maxWidth: 50,
      checkboxSelection: true,
      showDisabledCheckboxes: true,
      valueFormatter: e => '',
    },
    {
      field: 'subjectCode',
      headerName: 'Mã môn học',
      minWidth: 100,
      valueFormatter: e => {
        //console.log(e);
        return e.value ?? '-';
      },
    },
    {
      field: 'subjectName',
      headerName: 'Tên môn học',
      minWidth: 280,
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'subjectGroup',
      headerName: 'Nhóm môn học',
      minWidth: 100,
      cellClass: 'right-align',
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'STC',
      headerName: 'Số tín chỉ',
      minWidth: 100,
      cellClass: 'right-align',
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'remain',
      headerName: 'Còn lại',
      minWidth: 100,
      cellClass: 'right-align',
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'day',
      headerName: 'Thứ',
      minWidth: 100,
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'lessionStart',
      headerName: 'Tiết bắt đầu',
      minWidth: 100,
      cellClass: 'right-align',
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'lessions',
      headerName: 'Số tiết',
      minWidth: 100,
      cellClass: 'right-align',
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'room',
      headerName: 'Phòng',
      minWidth: 100,
      valueFormatter: e => e.value ?? '-',
    },
  ]
} 