import { DeleteFilled } from '@ant-design/icons';
import { Button, Tooltip } from 'antd';
import React, { } from 'react';

export const getColumnDefs2 = (gridRef, setOpenModalDelete, watch) => {

  return [
    {
      field: 'id',
      headerName: 'STT',
      maxWidth: 80,
      cellClass: 'align-right',
      valueFormatter: e => {
        console.log(e);
        return e.node.rowIndex + 1;
      },
    },
    {
      field: 'subject.code',
      headerName: 'Mã môn học',
      minWidth: 150,
      valueFormatter: e => {
        //console.log(e);
        return e.value ?? '-';
      },
    },
    {
      field: 'subject.name',
      headerName: 'Tên môn học',
      minWidth: 500,
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'groupCode',
      headerName: 'Nhóm môn học',
      minWidth: 100,
      cellClass: 'align-right',
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'subject.stc',
      headerName: 'Số tín chỉ',
      minWidth: 100,
      cellClass: 'align-right',
      valueFormatter: e => e.value ?? '-',
    },
    {
      field: 'status',
      headerName: 'Trạng thái',
      minWidth: 400,
      valueFormatter: e => e.data.remainQuantity === 0 ? 'Đã lưu vào CSDL' : "Chưa lưu vào CSDL",
    },
    {
      field: 'action',
      headerName: 'Hành động',
      width: 80,
      cellRenderer: e => {
        return <div className='action-wrapper'>
          <Tooltip title="Xoá lớp học phần" arrow>
            <div className='action-wrapper__item'>
              <Button className='btn-delete' type='text' onClick={() => setOpenModalDelete(true)}>
                <DeleteFilled />
              </Button>
            </div>
          </Tooltip>
        </div>
      }
    },
  ]
} 