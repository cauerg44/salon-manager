


export default function FormInput(props: any) {

  const {
    validation,
    invalid = "false",
    dirty = "false",
    onTurnDirty,
    ...inputProps
  } = props;

  function handleBluer() {
    onTurnDirty(props.name);
  }

  return (
    <input
      {...inputProps}
      data-invalid={invalid}
      data-dirty={dirty}
      onBlur={handleBluer} />
  );
}