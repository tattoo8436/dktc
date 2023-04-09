import { Button, Input, notification } from "antd";
import classNames from "classnames";
import React, { useState } from "react";
import { Controller, useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const Login = () => {
  const axiosClient = axios.create({
    headers: { "Content-Type": "application/json" },
  });

  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const loginSchema = yup.object().shape({
    username: yup.string().required("Tên đăng nhập là bắt buộc.").nullable(),
    password: yup.string().required("Mật khẩu là bắt buộc.").nullable(),
  });

  const {
    setValue,
    reset,
    watch,
    control,
    handleSubmit,
    formState: { errors },
  } = useForm({
    mode: "onChange",
    resolver: yupResolver(loginSchema),
    defaultValues: {
      username: "",
      password: "",
    },
  });

  const onSubmit = async (value) => {
    setLoading(true);
    //console.log(value);
    try {
      const { data } = await axiosClient.post(
        "http://26.192.253.140:8080/login",
        {
          username: value.username,
          password: value.password,
        }
      );
      console.log(data);
      localStorage.setItem("account", JSON.stringify(data));
      navigate("/home");
      setLoading(false);
      window.location.reload();
      //toast.success("Đăng nhập thành công!", { autoClose: 3000 });
      toast.dismiss();
    } catch (error) {
      toast.error("Tài khoản hoặc mật khẩu không chính xác!", {
        autoClose: 3000,
      });
      setLoading(false);
    }
  };

  return (
    <div className="login">
      <div className="login__header">Hệ thống đăng ký tín chỉ</div>

      <div className="login__content">
        <div className="login-form">
          <div className="login-form__header">Đăng nhập</div>
          <form onSubmit={handleSubmit(onSubmit)}>
            <div className="login-form__content">
              <div className="login-form__content__item">
                <div className="login-form__content__item__label">
                  Tên đăng nhập
                </div>

                <Controller
                  name="username"
                  control={control}
                  render={({ field }) => (
                    <Input
                      placeholder="Tên đăng nhập"
                      className={classNames(
                        "login-form__content__item__input",
                        {
                          "is-invalid": errors?.username?.type !== undefined,
                        }
                      )}
                      {...field}
                    />
                  )}
                />
                {errors.username && (
                  <p className="error-msg">{errors.username.message}</p>
                )}
              </div>

              <div className="login-form__content__item">
                <div className="login-form__content__item__label">Mật khẩu</div>

                <Controller
                  name="password"
                  control={control}
                  render={({ field }) => (
                    <Input.Password
                      placeholder="Mật khẩu"
                      className={classNames(
                        "login-form__content__item__input",
                        {
                          "is-invalid": errors?.password?.type !== undefined,
                        }
                      )}
                      {...field}
                    />
                  )}
                />
                {errors.password && (
                  <p className="error-msg">{errors.password.message}</p>
                )}
              </div>
            </div>

            <div className="login-form__footer">
              <Button
                className="btn-login"
                type="primary"
                htmlType="submit"
                loading={loading}
              >
                Đăng nhập
              </Button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
