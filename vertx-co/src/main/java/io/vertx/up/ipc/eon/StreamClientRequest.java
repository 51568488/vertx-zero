// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: zero.stream.proto

package io.vertx.up.ipc.eon;

/**
 * Protobuf type {@code io.vertx.up.ipc.eon.StreamClientRequest}
 */
public  final class StreamClientRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.vertx.up.ipc.eon.StreamClientRequest)
    StreamClientRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StreamClientRequest.newBuilder() to construct.
  private StreamClientRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StreamClientRequest() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StreamClientRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            io.vertx.up.ipc.eon.IpcEnvelop.Builder subBuilder = null;
            if (envelop_ != null) {
              subBuilder = envelop_.toBuilder();
            }
            envelop_ = input.readMessage(io.vertx.up.ipc.eon.IpcEnvelop.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(envelop_);
              envelop_ = subBuilder.buildPartial();
            }

            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.vertx.up.ipc.eon.UpStream.internal_static_io_vertx_up_ipc_eon_StreamClientRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.vertx.up.ipc.eon.UpStream.internal_static_io_vertx_up_ipc_eon_StreamClientRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.vertx.up.ipc.eon.StreamClientRequest.class, io.vertx.up.ipc.eon.StreamClientRequest.Builder.class);
  }

  public static final int ENVELOP_FIELD_NUMBER = 1;
  private io.vertx.up.ipc.eon.IpcEnvelop envelop_;
  /**
   * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
   */
  public boolean hasEnvelop() {
    return envelop_ != null;
  }
  /**
   * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
   */
  public io.vertx.up.ipc.eon.IpcEnvelop getEnvelop() {
    return envelop_ == null ? io.vertx.up.ipc.eon.IpcEnvelop.getDefaultInstance() : envelop_;
  }
  /**
   * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
   */
  public io.vertx.up.ipc.eon.IpcEnvelopOrBuilder getEnvelopOrBuilder() {
    return getEnvelop();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (envelop_ != null) {
      output.writeMessage(1, getEnvelop());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (envelop_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getEnvelop());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.vertx.up.ipc.eon.StreamClientRequest)) {
      return super.equals(obj);
    }
    io.vertx.up.ipc.eon.StreamClientRequest other = (io.vertx.up.ipc.eon.StreamClientRequest) obj;

    boolean result = true;
    result = result && (hasEnvelop() == other.hasEnvelop());
    if (hasEnvelop()) {
      result = result && getEnvelop()
          .equals(other.getEnvelop());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasEnvelop()) {
      hash = (37 * hash) + ENVELOP_FIELD_NUMBER;
      hash = (53 * hash) + getEnvelop().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.vertx.up.ipc.eon.StreamClientRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.vertx.up.ipc.eon.StreamClientRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code io.vertx.up.ipc.eon.StreamClientRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.vertx.up.ipc.eon.StreamClientRequest)
      io.vertx.up.ipc.eon.StreamClientRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.vertx.up.ipc.eon.UpStream.internal_static_io_vertx_up_ipc_eon_StreamClientRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.vertx.up.ipc.eon.UpStream.internal_static_io_vertx_up_ipc_eon_StreamClientRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.vertx.up.ipc.eon.StreamClientRequest.class, io.vertx.up.ipc.eon.StreamClientRequest.Builder.class);
    }

    // Construct using io.vertx.up.ipc.eon.StreamClientRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      if (envelopBuilder_ == null) {
        envelop_ = null;
      } else {
        envelop_ = null;
        envelopBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.vertx.up.ipc.eon.UpStream.internal_static_io_vertx_up_ipc_eon_StreamClientRequest_descriptor;
    }

    public io.vertx.up.ipc.eon.StreamClientRequest getDefaultInstanceForType() {
      return io.vertx.up.ipc.eon.StreamClientRequest.getDefaultInstance();
    }

    public io.vertx.up.ipc.eon.StreamClientRequest build() {
      io.vertx.up.ipc.eon.StreamClientRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public io.vertx.up.ipc.eon.StreamClientRequest buildPartial() {
      io.vertx.up.ipc.eon.StreamClientRequest result = new io.vertx.up.ipc.eon.StreamClientRequest(this);
      if (envelopBuilder_ == null) {
        result.envelop_ = envelop_;
      } else {
        result.envelop_ = envelopBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.vertx.up.ipc.eon.StreamClientRequest) {
        return mergeFrom((io.vertx.up.ipc.eon.StreamClientRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.vertx.up.ipc.eon.StreamClientRequest other) {
      if (other == io.vertx.up.ipc.eon.StreamClientRequest.getDefaultInstance()) return this;
      if (other.hasEnvelop()) {
        mergeEnvelop(other.getEnvelop());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.vertx.up.ipc.eon.StreamClientRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.vertx.up.ipc.eon.StreamClientRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.vertx.up.ipc.eon.IpcEnvelop envelop_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.vertx.up.ipc.eon.IpcEnvelop, io.vertx.up.ipc.eon.IpcEnvelop.Builder, io.vertx.up.ipc.eon.IpcEnvelopOrBuilder> envelopBuilder_;
    /**
     * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
     */
    public boolean hasEnvelop() {
      return envelopBuilder_ != null || envelop_ != null;
    }
    /**
     * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
     */
    public io.vertx.up.ipc.eon.IpcEnvelop getEnvelop() {
      if (envelopBuilder_ == null) {
        return envelop_ == null ? io.vertx.up.ipc.eon.IpcEnvelop.getDefaultInstance() : envelop_;
      } else {
        return envelopBuilder_.getMessage();
      }
    }
    /**
     * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
     */
    public Builder setEnvelop(io.vertx.up.ipc.eon.IpcEnvelop value) {
      if (envelopBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        envelop_ = value;
        onChanged();
      } else {
        envelopBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
     */
    public Builder setEnvelop(
        io.vertx.up.ipc.eon.IpcEnvelop.Builder builderForValue) {
      if (envelopBuilder_ == null) {
        envelop_ = builderForValue.build();
        onChanged();
      } else {
        envelopBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
     */
    public Builder mergeEnvelop(io.vertx.up.ipc.eon.IpcEnvelop value) {
      if (envelopBuilder_ == null) {
        if (envelop_ != null) {
          envelop_ =
            io.vertx.up.ipc.eon.IpcEnvelop.newBuilder(envelop_).mergeFrom(value).buildPartial();
        } else {
          envelop_ = value;
        }
        onChanged();
      } else {
        envelopBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
     */
    public Builder clearEnvelop() {
      if (envelopBuilder_ == null) {
        envelop_ = null;
        onChanged();
      } else {
        envelop_ = null;
        envelopBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
     */
    public io.vertx.up.ipc.eon.IpcEnvelop.Builder getEnvelopBuilder() {
      
      onChanged();
      return getEnvelopFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
     */
    public io.vertx.up.ipc.eon.IpcEnvelopOrBuilder getEnvelopOrBuilder() {
      if (envelopBuilder_ != null) {
        return envelopBuilder_.getMessageOrBuilder();
      } else {
        return envelop_ == null ?
            io.vertx.up.ipc.eon.IpcEnvelop.getDefaultInstance() : envelop_;
      }
    }
    /**
     * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.vertx.up.ipc.eon.IpcEnvelop, io.vertx.up.ipc.eon.IpcEnvelop.Builder, io.vertx.up.ipc.eon.IpcEnvelopOrBuilder> 
        getEnvelopFieldBuilder() {
      if (envelopBuilder_ == null) {
        envelopBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.vertx.up.ipc.eon.IpcEnvelop, io.vertx.up.ipc.eon.IpcEnvelop.Builder, io.vertx.up.ipc.eon.IpcEnvelopOrBuilder>(
                getEnvelop(),
                getParentForChildren(),
                isClean());
        envelop_ = null;
      }
      return envelopBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:io.vertx.up.ipc.eon.StreamClientRequest)
  }

  // @@protoc_insertion_point(class_scope:io.vertx.up.ipc.eon.StreamClientRequest)
  private static final io.vertx.up.ipc.eon.StreamClientRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.vertx.up.ipc.eon.StreamClientRequest();
  }

  public static io.vertx.up.ipc.eon.StreamClientRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StreamClientRequest>
      PARSER = new com.google.protobuf.AbstractParser<StreamClientRequest>() {
    public StreamClientRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StreamClientRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StreamClientRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StreamClientRequest> getParserForType() {
    return PARSER;
  }

  public io.vertx.up.ipc.eon.StreamClientRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

