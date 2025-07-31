package generated.yourhealth;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: YourHealth.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class YourHealthGrpc {

  private YourHealthGrpc() {}

  public static final String SERVICE_NAME = "YourHealth.YourHealth";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.yourhealth.ListOfMedicalTest,
      generated.yourhealth.AvailableAppointmentDate> getMedicalAdviceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MedicalAdvice",
      requestType = generated.yourhealth.ListOfMedicalTest.class,
      responseType = generated.yourhealth.AvailableAppointmentDate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.yourhealth.ListOfMedicalTest,
      generated.yourhealth.AvailableAppointmentDate> getMedicalAdviceMethod() {
    io.grpc.MethodDescriptor<generated.yourhealth.ListOfMedicalTest, generated.yourhealth.AvailableAppointmentDate> getMedicalAdviceMethod;
    if ((getMedicalAdviceMethod = YourHealthGrpc.getMedicalAdviceMethod) == null) {
      synchronized (YourHealthGrpc.class) {
        if ((getMedicalAdviceMethod = YourHealthGrpc.getMedicalAdviceMethod) == null) {
          YourHealthGrpc.getMedicalAdviceMethod = getMedicalAdviceMethod =
              io.grpc.MethodDescriptor.<generated.yourhealth.ListOfMedicalTest, generated.yourhealth.AvailableAppointmentDate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MedicalAdvice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.yourhealth.ListOfMedicalTest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.yourhealth.AvailableAppointmentDate.getDefaultInstance()))
              .setSchemaDescriptor(new YourHealthMethodDescriptorSupplier("MedicalAdvice"))
              .build();
        }
      }
    }
    return getMedicalAdviceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static YourHealthStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YourHealthStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YourHealthStub>() {
        @java.lang.Override
        public YourHealthStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YourHealthStub(channel, callOptions);
        }
      };
    return YourHealthStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static YourHealthBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YourHealthBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YourHealthBlockingStub>() {
        @java.lang.Override
        public YourHealthBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YourHealthBlockingStub(channel, callOptions);
        }
      };
    return YourHealthBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static YourHealthFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<YourHealthFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<YourHealthFutureStub>() {
        @java.lang.Override
        public YourHealthFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new YourHealthFutureStub(channel, callOptions);
        }
      };
    return YourHealthFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class YourHealthImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<generated.yourhealth.ListOfMedicalTest> medicalAdvice(
        io.grpc.stub.StreamObserver<generated.yourhealth.AvailableAppointmentDate> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getMedicalAdviceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMedicalAdviceMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                generated.yourhealth.ListOfMedicalTest,
                generated.yourhealth.AvailableAppointmentDate>(
                  this, METHODID_MEDICAL_ADVICE)))
          .build();
    }
  }

  /**
   */
  public static final class YourHealthStub extends io.grpc.stub.AbstractAsyncStub<YourHealthStub> {
    private YourHealthStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YourHealthStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YourHealthStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.yourhealth.ListOfMedicalTest> medicalAdvice(
        io.grpc.stub.StreamObserver<generated.yourhealth.AvailableAppointmentDate> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getMedicalAdviceMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class YourHealthBlockingStub extends io.grpc.stub.AbstractBlockingStub<YourHealthBlockingStub> {
    private YourHealthBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YourHealthBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YourHealthBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class YourHealthFutureStub extends io.grpc.stub.AbstractFutureStub<YourHealthFutureStub> {
    private YourHealthFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected YourHealthFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new YourHealthFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_MEDICAL_ADVICE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final YourHealthImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(YourHealthImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MEDICAL_ADVICE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.medicalAdvice(
              (io.grpc.stub.StreamObserver<generated.yourhealth.AvailableAppointmentDate>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class YourHealthBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    YourHealthBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.yourhealth.YourHealthServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("YourHealth");
    }
  }

  private static final class YourHealthFileDescriptorSupplier
      extends YourHealthBaseDescriptorSupplier {
    YourHealthFileDescriptorSupplier() {}
  }

  private static final class YourHealthMethodDescriptorSupplier
      extends YourHealthBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    YourHealthMethodDescriptorSupplier(String methodName) {
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
      synchronized (YourHealthGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new YourHealthFileDescriptorSupplier())
              .addMethod(getMedicalAdviceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
