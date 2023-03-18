import React, { } from 'react';

export const getColumnDefs2 = (gridRef) => {

  return [
    {
      field: 'id',
      headerName: 'STT',
      maxWidth: 60,
      valueFormatter: e => {
        return e.value ?? '-';
      },
    },
    {
      field: 'subjectCode',
      headerName: 'Mã môn học',
      minWidth: 120,
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
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'STC',
      headerName: 'Số tín chỉ',
      minWidth: 100,
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'status',
      headerName: 'Trạng thái',
      minWidth: 100,
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'action',
      headerName: 'Hành động',
      minWidth: 100,
      valueFormatter: e => e.value ?? '-',
    },
  ]
} 