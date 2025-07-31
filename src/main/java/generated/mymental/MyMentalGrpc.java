package generated.mymental;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: MyMental.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MyMentalGrpc {

  private MyMentalGrpc() {}

  public static final String SERVICE_NAME = "MyMental.MyMental";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.mymental.MentalIssue,
      generated.mymental.Advice> getMentalAdviceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MentalAdvice",
      requestType = generated.mymental.MentalIssue.class,
      responseType = generated.mymental.Advice.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.mymental.MentalIssue,
      generated.mymental.Advice> getMentalAdviceMethod() {
    io.grpc.MethodDescriptor<generated.mymental.MentalIssue, generated.mymental.Advice> getMentalAdviceMethod;
    if ((getMentalAdviceMethod = MyMentalGrpc.getMentalAdviceMethod) == null) {
      synchronized (MyMentalGrpc.class) {
        if ((getMentalAdviceMethod = MyMentalGrpc.getMentalAdviceMethod) == null) {
          MyMentalGrpc.getMentalAdviceMethod = getMentalAdviceMethod =
              io.grpc.MethodDescriptor.<generated.mymental.MentalIssue, generated.mymental.Advice>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MentalAdvice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.mymental.MentalIssue.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.mymental.Advice.getDefaultInstance()))
              .setSchemaDescriptor(new MyMentalMethodDescriptorSupplier("MentalAdvice"))
              .build();
        }
      }
    }
    return getMentalAdviceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MyMentalStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyMentalStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MyMentalStub>() {
        @java.lang.Override
        public MyMentalStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MyMentalStub(channel, callOptions);
        }
      };
    return MyMentalStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MyMentalBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyMentalBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MyMentalBlockingStub>() {
        @java.lang.Override
        public MyMentalBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MyMentalBlockingStub(channel, callOptions);
        }
      };
    return MyMentalBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MyMentalFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyMentalFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MyMentalFutureStub>() {
        @java.lang.Override
        public MyMentalFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MyMentalFutureStub(channel, callOptions);
        }
      };
    return MyMentalFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MyMentalImplBase implements io.grpc.BindableService {

    /**
     */
    public void mentalAdvice(generated.mymental.MentalIssue request,
        io.grpc.stub.StreamObserver<generated.mymental.Advice> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMentalAdviceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMentalAdviceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                generated.mymental.MentalIssue,
                generated.mymental.Advice>(
                  this, METHODID_MENTAL_ADVICE)))
          .build();
    }
  }

  /**
   */
  public static final class MyMentalStub extends io.grpc.stub.AbstractAsyncStub<MyMentalStub> {
    private MyMentalStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyMentalStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyMentalStub(channel, callOptions);
    }

    /**
     */
    public void mentalAdvice(generated.mymental.MentalIssue request,
        io.grpc.stub.StreamObserver<generated.mymental.Advice> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMentalAdviceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MyMentalBlockingStub extends io.grpc.stub.AbstractBlockingStub<MyMentalBlockingStub> {
    private MyMentalBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyMentalBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyMentalBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.mymental.Advice mentalAdvice(generated.mymental.MentalIssue request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMentalAdviceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MyMentalFutureStub extends io.grpc.stub.AbstractFutureStub<MyMentalFutureStub> {
    private MyMentalFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyMentalFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyMentalFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.mymental.Advice> mentalAdvice(
        generated.mymental.MentalIssue request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMentalAdviceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MENTAL_ADVICE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MyMentalImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MyMentalImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MENTAL_ADVICE:
          serviceImpl.mentalAdvice((generated.mymental.MentalIssue) request,
              (io.grpc.stub.StreamObserver<generated.mymental.Advice>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MyMentalBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MyMentalBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.mymental.MyMentalServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MyMental");
    }
  }

  private static final class MyMentalFileDescriptorSupplier
      extends MyMentalBaseDescriptorSupplier {
    MyMentalFileDescriptorSupplier() {}
  }

  private static final class MyMentalMethodDescriptorSupplier
      extends MyMentalBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MyMentalMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MyMentalGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MyMentalFileDescriptorSupplier())
              .addMethod(getMentalAdviceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
