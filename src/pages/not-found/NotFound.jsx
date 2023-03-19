import { Button, Result } from "antd";
import React from "react";
import { useNavigate } from "react-router-dom";

const NotFound = () => {
  const navigate = useNavigate();

  return (
    <Result
      status="404"
      title="404"
      subTitle="Trang này không tồn tại."
      extra={
        <Button type="primary" onClick={() => navigate("/")}>
          Trở lại
        </Button>
      }
    />
  );
};

export default NotFound;
